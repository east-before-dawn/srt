from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext

import urllib2
import re
import os.path

from tools import MultiPartForm

url = 'http://www.outofservice.com/bigfive/'
client_id = "client_id=d8f59b2af38f49e895ab39a30f2fdf33"
client_secret = "client_secret=cceb282da5d34baa8684e9bdde3cb038"
redirect_uri = "redirect_uri=http://apps.renren.com/testfive/test"
url_auth = "https://graph.renren.com/oauth"
usr_id = ""
flag = False

def get_authorize(request):
  url_getauth = urllib2.urlopen(url_auth +
      "/authorize?response_type=code&" + client_id + "&" +
      redirect_uri, timeout=20)
  get_grantcode = url_getauth.geturl()
  return HttpResponseRedirect(get_grantcode)

def test(request):
  global flag
  global usr_id
  if (not flag):
    urlnow = request.get_full_path()
    code = re.search(r'code=([0-9a-zA-Z]*)&', urlnow)
    auth_code = code.group(1)
    url_getid = url_auth + "/token?grant_type=authorization_code&code=" + auth_code + "&" + client_id + "&" + client_secret + "&" + redirect_uri
    get_id = urllib2.urlopen(url_getid, timeout=20)
    get_urltext = get_id.read()
    textfind = re.search(r'id":([0-9]*),', get_urltext)
    usr_id = textfind.group(1)
    flag = True
  return render_to_response('test.html',
      context_instance=RequestContext(request))

def post(request):
  global usr_id
  if not request.POST:
    return HttpResponseRedirect('/')

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
    return HttpResponseRedirect('/')
  fout = file(os.path.join(os.path.join(os.path.dirname(__file__), 'data'),
    usr_id + ".txt"), 'w')
  fout.write(str(list))
  return render_to_response('result.html', {
    'result' : list},
    context_instance=RequestContext(request));
  #except Exception as e:
  #  return HttpResponseRedirect('/')
