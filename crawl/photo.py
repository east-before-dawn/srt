import os
import sys
import time
import json

from base import Base
from common import get
from setting import data_path, url, interval, img_data_path

__all__ = ['Photo']

conv = lambda time: time.strptime(time, '%Y-%m-%d %H:%M:%S')

class Photo(Base):
  name = 'photo'
  page_size = 100

  def _crawl(self, token_list, user_id, album_id, until_time):
    response = []
    page = 1
    token = token_list[-1]
    while True:
      new_response = json.loads(get(url + self.name + '/list', [
        ('access_token', token),
        ('ownerId', user_id),
        ('albumId', album_id),
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

  def _get_album(self, user_id):
    with open(data_path+user_id+'/album.dat') as ifile:
      lines = ifile.readlines()
    return [str(json.loads(line.strip())['id']) for line in lines]

  def update(self, token_list, user_id, force):
    path = data_path + user_id + '/'
    if not os.path.exists(path):
      os.mkdir(path)

    album_list = self._get_album(user_id)
    for album in album_list:
      lines = None
      album_path = path + album + '/'
      if not os.path.exists(album_path):
        os.mkdir(album_path)

      filename = album_path + self.name + '.dat'
      if os.path.exists(filename):
        if not force and time.time() - os.path.getmtime(filename) < interval:
          print ('Info - ' + self.name + ' of User ' + user_id +
              ' is updated in last interval. No need to update.')
          return True
        with open(filename) as ifile:
          lines = ifile.readlines()

      import copy
      token_list_copy = copy.copy(token_list)
      response = self._crawl(token_list_copy, user_id, album,
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
    return None

  def _get_img_list(self, user_id, album):
    filename = data_path+user_id+'/'+album+'/'+self.name+'.dat'
    if not os.path.exists(filename):
      return []

    with open(filename) as ifile:
      lines = ifile.readlines()
    res = {}
    for line in lines:
      info = json.loads(line.strip())
      res[str(info['id'])] = info['images'][0]['url']
    return res

  def update_data(self, user_id):
    print 'get imgs of user ' + user_id
    album_list = self._get_album(user_id)
    for album in album_list:
      print 'get imgs in album ' + album + ' of user ' + user_id
      img_list = self._get_img_list(user_id, album)
      if not img_list:
        continue
      path = img_data_path + user_id + '/' + album + '/'
      if not os.path.exists(path):
        os.makedirs(path)
      for img_id, img_url in img_list.iteritems():
        filename = path + img_id + '.jpg'
        if os.path.exists(filename):
          continue
        with open(filename, 'wb') as ofile:
          ofile.write(get(img_url, {}))
