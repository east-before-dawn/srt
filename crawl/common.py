import urllib2
import json

from setting import urlopen_timeout, urlopen_try_times

__all__ = ['url', 'get', 'crawl_by_list']

def get(url, args):
  url += '?'
  for k, v in args:
    url += k + '=' + v + '&'

  try_times = urlopen_try_times
  while try_times > 0:
    try:
      print 'try get url: ' + url
      response = urllib2.urlopen(url, timeout=urlopen_timeout).read()
      return response
    except Exception as e:
      print 'ERROR - in url open'
      print e
      try_times -= 1
  print 'ERROR - still error in urlopen, give up'
  return '{}'

