from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext

import urllib2
import re
import os.path
import thread
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

def get_data(usr_id, token):
    sys.path.append('../')
    from crawl import Crawl
    import time
    c = Crawl()
    print 'Start web crawl.'
    c.update([usr_id], token_list=[token])
    c.update_img([usr_id], token_list=[token])
    c.update_voice([usr_id], token_list=[token])
    print 'Crawl is finished.'
    
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

def get_authorize(request):
  return HttpResponseRedirect(url_auth + '/grant?response_type=code&' +
      client_id + '&' + redirect_uri)

def auth(request):
    urlnow = request.get_full_path()
    code = re.search(r'code=([0-9a-zA-Z]*)', urlnow)
    auth_code = code.group(1)
    url_getid = (url_auth + '/token?grant_type=authorization_code&code=' +
                 auth_code + '&' + client_id + '&' + client_secret + '&' + redirect_uri)
    get_id = urllib2.urlopen(url_getid, timeout=20)
    get_urltext = get_id.read()
    textfind = re.search(r'id":([0-9]*),', get_urltext)
    usr_id = textfind.group(1)
    token = re.search(r'access_token":"(.*)"', get_urltext).group(1)
    response = HttpResponseRedirect('/index/')
    response.set_cookie('usr_id', usr_id)
    response.set_cookie('token', token)
    thread.start_new_thread(get_data, (usr_id, token))
    return response

def test(request):
  return render_to_response('index.html',
      context_instance=RequestContext(request))

def Ttest(request):
    return render_to_response('Ttest.html',
                              context_instance=RequestContext(request))

def index(request):
    global finished
    finished = False
    return render_to_response('index.html',
                              context_instance=RequestContext(request))

def about(request):
    return render_to_response('about.html',
                              context_instance=RequestContext(request))


def loading(request):
    global finished
    if finished:
        return HttpResponseRedirect('/result/')
    else:
        return render_to_response('loading.html',
                              context_instance=RequestContext(request))

def result(request):
    if not finished:
        return HttpResponseRedirect('/')
    #draw_it(five_result, filename='templates/result.png')
    file_object = open('server/static/images/data.csv', 'w')
    file_object.write('age,population\n')
    file_object.write('O,' + str((int)(five_result['O']* 1000000)) + '\n')
    file_object.write('C,' + str((int)(five_result['C']* 1000000)) + '\n')
    file_object.write('E,' + str((int)(five_result['E']* 1000000)) + '\n')
    file_object.write('A,' + str((int)(five_result['A']* 1000000)) + '\n')
    file_object.write('N,' + str((int)(five_result['N']* 1000000)) + '\n')
    file_object.close
    return render_to_response('result.html',
                              {'result':five_result})

def post(request):
  finished = True
  for i in range(1, 46):
    if i < 10:
      key = 'bigfive-me-0'+str(i)
    else:
      key = 'bigfive-me-'+str(i)
    if key not in request.POST:
      finished = False
      break
  if not finished:
    checked = []
    for i in range(1, 46):
      if i < 10:
        key = 'bigfive-me-0'+str(i)
      else:
        key = 'bigfive-me-'+str(i)
      checked.append(request.POST.get(key, 0))
    return render_to_response('test.html',
        {'status' : 'NOTFINISHED',
         'checked' : checked},
        context_instance=RequestContext(request))

  form = MultiPartForm()
  for key in request.POST:
    form.add_field(key, request.POST[key])

  url_request = urllib2.Request(url)
  body = str(form)
  url_request.add_header('content-type', form.get_content_type())
  url_request.add_header('content-length', str(len(body)))
  url_request.add_data(body)

  #try:
  url_response = urllib2.urlopen(url_request, timeout=20)
  get_url = url_response.geturl()
  iter = re.finditer(r'R=([\d\.]*)&', get_url)
  list = [i.group(1) for i in iter]
  if not list:
    return render_to_response('test.html',
        {'status' : status},
        context_instance=RequestContext(request))

  dic = dict(zip('OCEAN', list))
  dic['M'] = request.POST['mark']
  fout = file(os.path.join(os.path.join(os.path.dirname(__file__), 'data'),
    request.COOKIES['usr_id'] + '.txt'), 'w')
  fout.write(str(dic))

  return render_to_response('result.html', {
    'result' : dic},
    context_instance=RequestContext(request))
  #except Exception as e:
  #  return HttpResponseRedirect('/test/')
