import java.security.MessageDigest;

public class Block{
	private String data;
	private String hash;
	private String prevHash;
	
	public Block(String data, String prevHash){
		this.data = d;
		this.setHash();
		this.prevHash = prevHash;
	}
	
	public setHash(){
		//this.hash = hash(this.data)
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
}
