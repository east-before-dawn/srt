import os
import argparse

from setting import user_list_path, base_token
from status import Status
from blog import Blog
from share import Share
from profile import Profile

__all__ = ['Crawl']

crawl_list = [
    Status(),
    Blog(),
    Share(),
    #Profile(),
]

class Crawl(object):
  token = base_token

  def set_token(self, token):
    self.token = token

  def _get_all_user(self):
    for root, dirs, files in os.walk(user_list_path):
      return [f[:f.rfind('.')] for f in files]

  def update(self, user_list=None, force=False):
    if user_list is None:
      user_list = self._get_all_user()
    for user in user_list:
      for crawl_item in crawl_list:
        crawl_item.update(self.token, user, force)

if __name__ == '__main__':
  parser = argparse.ArgumentParser(
      description=''
  )
  parser.add_argument('-t', '--token',
      type=str,
      help='access token',
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
  if args.token is not None:
    crawl.set_token(args.token)
  crawl.update(args.user, args.force)
