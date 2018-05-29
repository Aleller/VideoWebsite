package model;

/**
 * 由于投稿视频后，不能在视频播放页马上播放该视频
 * 所以编写这个类，这个只有一个方法：change()
 * 这个方法会在web.xml最后一行加上一行空行
 * tomcat就会自动检测到web.xml被修改了，就会update resources
 * 然后就可以在播放页面马上观看这个视频啦！
 */

public class changeWebxml {
}
