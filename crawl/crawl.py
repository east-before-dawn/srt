import os
import argparse

from setting import user_list_path, token_list
from status import Status
from blog import Blog
from share import Share
from profile import Profile

__all__ = ['Crawl']

crawl_list = [
    Status(),
    Blog(),
    Share(),
    Profile(),
]

class Crawl(object):
  def _get_all_user(self):
    for root, dirs, files in os.walk(user_list_path):
      return [f[:f.rfind('.')] for f in files]

  def update(self, user_list=None, force=False):
    if user_list is None:
      user_list = self._get_all_user()
    token_num = 0
    for user in user_list:
      for crawl_item in crawl_list:
        if not crawl_item.update(token_list, user, force):
          if not token_list:
            print 'Exit.'
            return False
    return True

if __name__ == '__main__':
  parser = argparse.ArgumentParser(
      description=''
  )
  parser.add_argument('-u', '--user',
      type=str,
      nargs='+',
      help='a list of userid that needs to update',
  )
  parser.add_argument('-f', '--force',
      action='store_true',
      default=False,
      help='force ignore the last modified time or not',
  )
  args = parser.parse_args()

  crawl = Crawl()
  crawl.update(args.user, args.force)