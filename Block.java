public class Block{
	private String data;
	private String hash;
	private String prevHash;
	
	public Block(String d){
		this.data = d;
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
