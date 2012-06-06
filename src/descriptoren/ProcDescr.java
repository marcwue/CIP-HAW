package descriptoren;

public class ProcDescr extends AbstractDescr {

	private String name;
	private int StartAddress; 
	private int lengthParaBlock; 
	private int framesize;
	private VarDescr params;
	
	/**
	 * @param size
	 * @param name
	 * @param startAddress
	 * @param lengthParaBlock
	 * @param framesize
	 * @param params
	 */
	public ProcDescr(int size, String name, int startAddress,
			int lengthParaBlock, int framesize, VarDescr params) {
		this.name = name;
		StartAddress = startAddress;
		this.lengthParaBlock = lengthParaBlock;
		this.framesize = framesize;
		this.params = params;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the startAddress
	 */
	public int getStartAddress() {
		return StartAddress;
	}

	/**
	 * @return the lengthParaBlock
	 */
	public int getLengthParaBlock() {
		return lengthParaBlock;
	}

	/**
	 * @return the framesize
	 */
	public int getFramesize() {
		return framesize;
	}

	/**
	 * @return the params
	 */
	public VarDescr getParams() {
		return params;
	}
	
	
	
}
