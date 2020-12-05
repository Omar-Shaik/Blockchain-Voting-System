import java.util.ArrayList;

public class Blockchain{
	private ArrayList<Block> chain;
	private int tail;
	
	public Blockchain(){
		chain = new ArrayList<>();
		Block genesis = new Block("Let the games begin", "0");
		chain.add(genesis);
		tail = 0;
	}
	
	public int getLength(){
		return this.tail+1;
	}
	
	public String getBlock(int blockNum){
		return(chain.get(blockNum).getData());
	}
	
	public void addBlock(String data){
		String prev = this.chain.get(this.tail).getHash();
		Block b = new Block(data, prev);
		chain.add(b);
		this.tail++;
		verifyChain();
	}
	
	public void verifyChain(){
		ArrayList<Block> tempChain = new ArrayList<Block>();
		for(int i = 1; i < chain.size();i++){
			tempChain.add(chain.get(i));
			if(!chain.get(i).getPrevHash().equals(chain.get(i-1).getHash())){
				chain = tempChain;
			}
		}
	}
}