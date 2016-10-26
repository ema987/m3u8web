M3U8Web
=

M3U8Web is a simple SpringBoot application which serves an M3U8 playlist. A playlist is firstly retrieved from an external link, cached, and then it can be served again. Eventually, the playlist can be modified before being served using the M3U8Parser library (or in some other way!).

Since this project is using my M3U8Parser library, the served playlist will be in the format here described http://ss-iptv.com/en/users/documents/m3u

You can also avoid to use my external library and do your own changes to the playlist, or you can consider this project as a simple SpringBoot application, too.

Main classes
-

 - DownloadPlaylistController: this is the class which maps the APP_URL/playlist url request to this class.

How to use it
-
Clone the repository, change the configuration.json file adding the URLs of the playlists you want to edit/merge and the categories you want to remove from the final playlist. Then build the project and deploy it, that's it!

Contributions
-
Pull requests are welcome

License
-
Apache License 2.0

        
