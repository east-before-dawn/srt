from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext

import re
import thread
import urllib2
import sys
import os

from tools import MultiPartForm

url = 'http://www.outofservice.com/bigfive/'
client_id = 'client_id=d8f59b2af38f49e895ab39a30f2fdf33'
client_secret = 'client_secret=cceb282da5d34baa8684e9bdde3cb038'
#redirect_uri = 'redirect_uri=http://166.111.139.110:8000/auth'
# below is for test: bigfivetest
#client_id = 'client_id=8b8031a73dc544bebcb356e3a65855d6'
#client_secret = 'client_secret=7e7ccb94afa5465abaa14a991275935a'
redirect_uri = 'redirect_uri=http://127.0.0.1:8000/auth'
url_auth = 'http://graph.renren.com/oauth'

finished = False
five_result = None

def get_authorize(request):
  return HttpResponseRedirect(url_auth + '/grant?response_type=code&' +
      client_id + '&' + redirect_uri)

def get_data(usr_id, token):
  import sys
  sys.path.append('../')
  from crawl import Crawl
  c = Crawl()
  print 'Start web crawl.'
  c.update([usr_id], token_list=[token])
  print 'Crawl is finished.'

  import os
  print 'Start analysis.'
  #os.system('java -Djava.ext.dirs=../../predict/lib -jar ../../predict/predictor.jar ../../analysis/data_json/'+usr_id)
  os.system('java -Djava.ext.dirs=./lib -jar predictor.jar ../../analysis/data_json/'+usr_id)
  print 'Analysis is finished.'

  global five_result
  #with open('../../predict/predict_result/'+usr_id+'.txt') as ifile:
  with open('predict_result/'+usr_id+'.txt') as ifile:
    five_result = eval(ifile.read())
  global finished
  finished = True

def auth(request):
  urlnow = request.get_full_path()
  import re
  import urllib2
  code = re.search(r'code=([0-9a-zA-Z]*)', urlnow)
  auth_code = code.group(1)
  url_getid = (url_auth + '/token?grant_type=authorization_code&code=' +
    auth_code + '&' + client_id + '&' + client_secret + '&' + redirect_uri)
  get_id = urllib2.urlopen(url_getid, timeout=20)
  get_urltext = get_id.read()
  textfind = re.search(r'id":([0-9]*),', get_urltext)
  usr_id = textfind.group(1)
  token = re.search(r'access_token":"(.*)"', get_urltext).group(1)
  response = HttpResponseRedirect('/load/')
  response.set_cookie('usr_id', usr_id)
  response.set_cookie('token', token)
  return response

def load(request):
  import thread
  thread.start_new_thread(get_data, (
    request.COOKIES['usr_id'], request.COOKIES['token']))
  global finished
  if finished:
    return HttpResponseRedirect('/result/')
  else:
    return render_to_response('load.html')

def result(request):
  return render_to_response('result.html',
      {'result':five_result})
