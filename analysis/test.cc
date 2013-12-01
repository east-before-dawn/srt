#include "status.h"
#include "blog.h"
#include "share.h"
#include "profile.h"
#include "sys/types.h"
#include "dirent.h"
#include <cstring>

int main() {
  DIR *dir;
  struct dirent *pFiles;
  dir = opendir("data/");
  while ((pFiles = readdir(dir)) != NULL) {
    if (pFiles->d_name[0] == '.') continue;
    char name[10];
    memset(name, 0, sizeof(name));
    strcpy(name, pFiles->d_name);
    char path[100];
    memset(path, 0, sizeof(path));
    strcpy(path, "data/");
    strncat(path, name, 5);
    strncat(path, "/", 1);
    get_status_info(path);    
    get_blog_info(path);
    get_share_info(path);
    get_profile_info(path);
  }
  return 0;
}
