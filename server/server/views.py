from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext

import urllib2
import re

from tools import MultiPartForm

url = 'http://www.outofservice.com/bigfive/'

def test(request):
  return render_to_response('test.html', context_instance=RequestContext(request));

def post(request):
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

  try:
    url_response = urllib2.urlopen(url_request, timeout=20)
    page = url_response.read()
    iter = re.finditer(r'Your percentile: (\d*)<', page)
    list = [i.group(1) for i in iter]
    return render_to_response('result.html', {
      'result' : list},
      context_instance=RequestContext(request));
  except Exception as e:
    return HttpResponseRedirect('/')
