package general;

import java.util.Stack;

public class ValidParantheses {

	public static void main(String[] args) {
		String str = "([)";
		ValidParantheses validParantheses = new ValidParantheses();
		System.out.println(validParantheses.isValid(str));
	}
	
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
        	if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='[')
        		stack.push(s.charAt(i));
        	else if(s.charAt(i)==')'||s.charAt(i)=='}'||s.charAt(i)==']') {
        		if(!stack.isEmpty()) {
        			if(stack.lastElement()==getPair(s.charAt(i)))
        				stack.pop();
        			else
        				return false;
        		} else
        			return false;
        	}
        }
        if(stack.isEmpty())
        	return true;
        return false;
    }

	private Character getPair(char c) {
		Character cr= 0;
		if(c==')')
			cr = '(';
		else if(c=='}')
			cr = '{';
		else if(c==']')
			cr = '[';
		return cr;
	}

}
