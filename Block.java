import java.security.MessageDigest;
import java.util.*;

public class Block{
	private String data;
	private String hash;
	private String prevHash;
	private String time;
	private int nonce;
	
	public Block(String data, String prevHash, String time){
		this.data = data;
		this.time = time;
		this.setHash();
		this.prevHash = prevHash;
	}
	
	public Block(String data, String hash, String prevHash, String time, int nonce){
		this.data = data;
		this.hash = hash;
		this.prevHash = prevHash;
		this.time = time;
		this.nonce = nonce;
	}
	
	public String getData(){
		return this.data;
	}
	
	public String getHash(){
		return this.hash;
	}
	
	public String getPrevHash(){
		return this.prevHash;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public int getNonce(){
		return this.nonce;
	}
	
	public void setHash(){
		try{
			String hashed = data + time;
			MessageDigest d = MessageDigest.getInstance("SHA-256");
			d.update(hashed.getBytes());
			byte[] result = d.digest();
			StringBuffer s = new StringBuffer();
			for(byte b:result){
				s.append(Integer.toHexString(b & 0xff).toString());
			}
			this.hash = s.toString();
		}catch(Exception e){
			System.out.println("Error Occured");
			e.printStackTrace();
		}
	}
	
	public void setNonce(int nonce){
		this.nonce = nonce;
	}
}