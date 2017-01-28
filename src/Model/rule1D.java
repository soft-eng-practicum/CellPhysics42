package Model;
import exception.NotValidRuleException;

public class rule1D
{
	byte state7;
	byte state6;
	byte state5;
	byte state4;
	byte state3;
	byte state2;
	byte state1;
	byte state0;
	int rule;
	
	public rule1D(int rule)
	{
		setRule(rule);
		String binary = toBinary(rule);
		state7 = Byte.parseByte(binary.substring(binary.length()-8, binary.length()-7));
		state6 = Byte.parseByte(binary.substring(binary.length()-7, binary.length()-6));
		state5 = Byte.parseByte(binary.substring(binary.length()-6, binary.length()-5));
		state4 = Byte.parseByte(binary.substring(binary.length()-5, binary.length()-4));
		state3 = Byte.parseByte(binary.substring(binary.length()-4, binary.length()-3));
		state2 = Byte.parseByte(binary.substring(binary.length()-3, binary.length()-2));
		state1 = Byte.parseByte(binary.substring(binary.length()-2, binary.length()-1));
		state0 = Byte.parseByte(binary.substring(binary.length()-1, binary.length()));		
	}
	

	public void setRule(int rule)
	{
		this.rule = rule;
	}


	public byte isOn(byte one, byte two, byte three) throws NotValidRuleException
	{
		
		if(one == 1 && two == 1 && three == 1)
			return state7;
		else if(one == 1 && two == 1 && three == 0)
			return state6;
		else if(one == 1 && two == 0 && three == 1)
			return state5;
		else if(one == 1 && two == 0 && three == 0)
			return state4;
		else if(one == 0 && two == 1 && three == 1)
			return state3;
		else if(one == 0 && two == 1 && three == 0)
			return state2;
		else if(one == 0 && two == 0 && three == 1)
			return state1;
		else if(one == 0 && two == 0 && three == 0)
			return state0;
		else
			throw new NotValidRuleException("Bad input for isOn method");

	}

	@Override
	public String toString()
	{
		return "rule1D [state7=" + state7 + ", state6=" + state6 + ", state5=" + state5 + ", state4=" + state4
				+ ", state3=" + state3 + ", state2=" + state2 + ", state1=" + state1 + ", state0=" + state0 + "]";
	}



	private String toBinary(int rule)
	{
		return String.format("%16s", Integer.toBinaryString(rule)).replace(' ', '0');
	}

}
