import os
import sys
import json
import re
import time
import urllib
import urllib2
import cookielib

from base import Base
from common import get
from setting import data_path, url, interval, voice_data_path

__all__ = ['Photo']

class Voice(Base):
  name = 'voice'

  def _login(self):
    cj = cookielib.LWPCookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    opener.open('http://www.renren.com/PLogin.do', urllib.urlencode({
      'email':'CatherineBell@163.com',
      'password':'CatherineBella',
    }))

  def _crawl(self, user_id, album_id):
    self._login()
    page = get('http://photo.renren.com/photo/'+user_id+'/album-'+album_id)
    first_photo_url = re.search('http://photo.renren.com/photo/'+user_id+
        '/photo-.*false', page).group()
    page = get(first_photo_url)
    file_url = re.findall('voiceUrl":"(.*?.mp3)"', page)
    return [url+'\n' for url in file_url]

  def _get_album(self, user_id):
    with open(data_path+user_id+'/album.dat') as ifile:
      lines = ifile.readlines()
    res = []
    for line in lines:
      album = json.loads(line.strip())
      if album['type'] == 'VOICE':
        res.append(str(album['id']))
    return res

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

      new_lines = self._crawl(user_id, album)

      if lines is not None:
        new_lines.extend(lines)
      reload(sys)
      sys.setdefaultencoding('utf-8')
      file(filename, 'w').writelines(new_lines)
    return True

  def _get_voice_list(self, user_id, album):
    filename = data_path+user_id+'/'+album+'/'+self.name+'.dat'
    if not os.path.exists(filename):
      return []

    with open(filename) as ifile:
      lines = ifile.readlines()
    return [line.strip() for line in lines]

  def update_data(self, user_id):
    print 'get voices of user ' + user_id
    album_list = self._get_album(user_id)
    for album in album_list:
      print 'get voices in album ' + album + ' of user ' + user_id
      voice_list = self._get_voice_list(user_id, album)
      if not voice_list:
        continue
      path = voice_data_path + user_id + '/' + album + '/'
      if not os.path.exists(path):
        os.makedirs(path)
      for voice_url in voice_list:
        filename = path + voice_url[voice_url.rfind('/'):] + '.mp3'
        if os.path.exists(filename):
          continue
        with open(filename, 'wb') as ofile:
          ofile.write(get(voice_url, {}))
