#include <iostream>
#include "sys/types.h"
#include "dirent.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <ctime>
using namespace std;

void get_profile_info(const char * path) {
  char file_name[100];
  memset(file_name, 0, sizeof(file_name));
  strcpy(file_name, path);
  strncat(file_name, "/profile.dat", sizeof("/share.dat"));
  freopen(file_name, "r", stdin);

  int star, work, like, appcount,visitorcount,pagecount,zhancount,musiccount,moviecount,friendcount;
  double basicinfo, education, density;

  string name;
  // Get name
  while (getline(cin, name)) {
    scanf("%d %lf %lf %d %d %d %d %d %d %d %d %d %lf ", &star, &basicinfo, &education, &work, &like, &appcount, &visitorcount, &pagecount, &zhancount,
        &musiccount, &moviecount, &friendcount, &density);
  }
  // all of above
  // **num <= 1**
  return;
}
