from django.conf.urls import patterns, include, url
from views import *

#from django.contrib import admin
#admin.autodiscover()

urlpatterns = patterns('',
    (r'^$', get_authorize),
    (r'^auth/$', auth),
    (r'^load/$', load),
    (r'^result/$', result),

    # Examples:
    # url(r'^$', 'testfive.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    #url(r'^admin/', include(admin.site.urls)),
)
