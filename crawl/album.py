import os
import sys
import time
import json

from base import Base
from setting import data_path, interval

__all__ = ['Album']

class Album(Base):
  name = 'album'
  page_size = 100

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

    import copy
    token_list_copy = copy.copy(token_list)
    response = self._crawl(token_list_copy, user_id,
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

  def _normalize(self, responses):
    return [json.dumps(item) + '\n' for item in responses]

  def _get_time(self, lines):
    if lines is not None and len(lines) > 3:
      return lines[3][:-1]
