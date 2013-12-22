import os
import sys
import time
import json

from common import get
from setting import data_path, url, interval

__all__ = ['Base']

conv = lambda time: time.strptime(time, '%Y-%m-%d %H:%M:%S')

class Base(object):
  name = None
  page_size = 20

  def _crawl(self, token_list, user_id, until_time):
    response = []
    page = 1
    token = token_list[-1]
    while True:
      new_response = json.loads(get(url + self.name + '/list', [
        ('access_token', token),
        ('ownerId', user_id),
        ('pageSize', str(self.page_size)),
        ('pageNumber', str(page)),
      ]))
      if 'error' in new_response and new_response['error'] == 403:
        token_list.pop()
        if not token_list:
          print 'Error - All token can not be used now.'
          return None
        token = token_list[-1]
        continue
      if 'response' not in new_response:
        break
      new_response = new_response['response']
      if not new_response:
        break
      if until_time is not None:
        conv_until_time = conv(until_time)
        if conv(new_response[-1]['createTime']) <= conv_until_time:
          for item in new_response:
            if item['createTime'] <= conv_until_time:
              break
          response.append(item)
          return response
      else:
        response.extend(new_response)
      page += 1
    return response

  def update(self, token_list, user_id, force):
    path = data_path + user_id + '/'
    if not os.path.exists(path):
      os.mkdir(path)
    lines = None
    filename = path + self.name + '.dat'
    if os.path.exists(filename):
      if not force and time.time() - os.path.getmtime(filename) < interval:
        print ('Info - ' + self.name + ' of User ' + user_id +
            ' is updated in last interval. No need to update.')
        return True
      lines = file(filename, 'r').readlines()

    response = self._crawl(token_list, user_id,
      self._get_time(lines))
    if response is None:
      return False
    new_lines = self._normalize(response)

    if lines is not None:
      new_lines.extend(lines)
    reload(sys)
    sys.setdefaultencoding('utf-8')
    file(filename, 'w').writelines(new_lines)
    return True