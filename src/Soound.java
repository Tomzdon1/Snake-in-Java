import java.applet.Applet;
import java.applet.AudioClip;

public class Soound {
	public static final Soound sound1=new Soound("/dymek.wav");
	public static final Soound sound2=new Soound("/Game_Over.wav");
	public static final Soound sound3=new Soound("/level_up.wav");
	private AudioClip clip;
	public Soound(String filename){
		try{
			clip=Applet.newAudioClip(Soound.class.getResource(filename));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
public void play(){
	try{
		new Thread(){
			public void run(){
				clip.play();
			}
		}.start();
	}catch(Exception ex){
		ex.printStackTrace();
	}
}

}