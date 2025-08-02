
import javafoundations.ArrayStack;
/**
 * Checks whether a word is a palindrome using ArrayStack.
 *
 * @author Ashley Yang and Bessie Li
 * @version 3/10/24
 */
public class PalindromeChecker
{
    private ArrayStack<Character> stack1;        
    private ArrayStack<Character> stack2;
    private String word;
    /**
     * Constructor for objects of class PalindromeChecker that initializes the 2 stacks and the word to be checked
     * 
     * @param String word to be checked
     */
    public PalindromeChecker(String word)
    {   
        this.word = word.toLowerCase();
        stack1 = new ArrayStack<>();
        stack2 = new ArrayStack<>();
    }

    /**
     * Checks if a specific word is a palindrome using 2 stacks (1 for the front half of the word, 1 for the back half) and checking if their contents are equal
     *
     * @return boolean (true if is palindrome, false if not)
     */
    public boolean isPalindrome()
    {
        if(word.length() == 0){
            return false;
        }else {
            stack1 = getComparisonStack();
            //sets the comparisonStack to the stack1 variable
            for (int i = word.length() - 1; i > word.length()/2; i--) {
                //Does not matter if it is an odd numbered word; the i > word.length()/2 makes it so that the middle letter
                //does not get accessed and pushed onto stack2 
                stack2.push(word.charAt(i));
            }
            while (!stack1.isEmpty() && !stack2.isEmpty()) {
                if (stack1.pop() != stack2.pop()) {
                    //If the popped value are not equal, we halt the process and return false
                    return false;
                }
            }
            //Returns true if the stacks are empty as that means we have declared all the characters to be equal as we've popped them all
            return (stack1.isEmpty() && stack2.isEmpty());
        }
    }

    /**
     * Helper method: creates the stack to compare to by splitting the char array in half and pushing characters from the first half of the array
     * 
     * @return ArrayStack<Character> (comparison stack)
     */
    private ArrayStack<Character> getComparisonStack(){
        char[] array = word.toCharArray();
        for(int i=0; i<word.length()/2; i++){
            //Again, does not matter if it is odd numbered word, like madam, when we split the array for constructing the comparison stacks; 
            //we do not end up iterating through the middle letter anyways thanks to our for loop (i<array.length)/2).
            this.stack1.push(array[i]);
        }
        return stack1;
    }   
    
    /**
     * Creates a string representation of testing if a word is a palindrome
     * 
     * @return String (string representation)
     */
    public String toString(){
        String s ="";

        s+= "The word "+ word + " is a palindrome: " + isPalindrome();
        return s;
    }

    public static void main (String[] args){
        try{
            PalindromeChecker checker = new PalindromeChecker(args[0]);
            System.out.print("\nTesting isPalindrome() for " + args[0] + ": ");
            boolean result = checker.isPalindrome();
            System.out.println(result);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Please include a command line argument for testing palindrome ");
        }
    }
}
