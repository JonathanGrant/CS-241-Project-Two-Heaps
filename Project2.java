import java.util.Scanner; //for user menu choice
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.sound.sampled.AudioFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.File;
import java.lang.Math; //for random numbers
import java.net.MalformedURLException;

//Main class file, pushes the whole program into motion
public class Project2 {
	public static int OptimalSwaps = 0; // total number of swaps, to be divided
										// and averaged later
	public static int SequentialSwaps = 0;

	//calls animation to start the whole program
	public static void main(String[] args) throws Exception {
		PaintTitleMovie q = new PaintTitleMovie();
		q.main(new String[1]); //load main method of animation to start animation
	}

	//method is to be called from menu class
	//this allows user to choose from the GUI menu
	//runs the heap methods
	public static void run(String choice) { // runs program, is called from a
											// graphics window
		String menuChoice = choice.trim(); // remove unnecessary spaces
		int nums[]; // number array to be used
		int numsbynums[][]; // secondary array

		if (menuChoice.equals("1")) {
			// choice 1
			// generate random numbers into int array x 20
			numsbynums = new int[20][100]; // set size
			for (int a = 0; a < 20; a++) {
				for (int b = 0; b < 100; b++) {
					numsbynums[a][b] = (int) (Math.random() * 400) + 1; // nums
																		// 1-400
				}
			}
			// sequential insertion x 20
			int seq[][] = new int[20][100];
			for (int a = 0; a < 20; a++) {
				seq[a] = sequentialInsertion(numsbynums[a]);
			}
			// optimal method x 20
			int opt[][] = new int[20][100];
			for (int a = 0; a < 20; a++) {
				opt[a] = optimalMethod(numsbynums[a]);
			}
			// output avg number of swaps for both methods
			System.out.println("\nSequential Insertion Swaps: "
					+ (SequentialSwaps / 20));
			System.out
					.println("\nOptimal Method Swaps: " + (OptimalSwaps / 20));
			PaintOptionOne go = new PaintOptionOne(OptimalSwaps,
					SequentialSwaps);
			OptimalSwaps = 0; //reset
			SequentialSwaps = 0;
		} else if (menuChoice.equals("2")) {
			// choice 2
			// create 1-100 sequential array
			nums = new int[100];
			for (int a = 0; a < 100; a++) {
				nums[a] = a + 1;
			}
			// sequential insertion
			int[] seq = sequentialInsertion(nums);
			int[] seq1 = new int[10];
			// optimal method
			int[] opt = optimalMethod(nums);
			int[] opt1 = new int[10];
			// output first 10 integers for both methods
			System.out.println();
			System.out.print("Sequential Insertion Array: ");
			for (int a = 0; a < 10; a++) {
				System.out.print(seq[a] + " ");
				seq1[a] = seq[a];
			}
			System.out.println();
			System.out.print("Optimal Method Array: ");
			for (int a = 0; a < 10; a++) {
				System.out.print(opt[a] + " ");
				opt1[a] = opt[a];
			}
			// output number of swaps
			System.out.println("\n\nSequential Insertion Swaps: "
					+ (SequentialSwaps / 20));
			System.out.println("Optimal Method Swaps: " + (OptimalSwaps / 20));
			// 10 removals on heap
			for (int a = 0; a < 10; a++) {
				seq = deleteRoot(seq);
				opt = deleteRoot(opt);
			}
			// output first 10 again
			System.out.println("\nAfter Deletion: ");
			// output first 10 integers for both methods
			System.out.print("Sequential Insertion Array: ");
			for (int a = 0; a < 10; a++) {
				System.out.print(seq[a] + " ");
			}
			System.out.println();
			System.out.print("Optimal Method Array: ");
			for (int a = 0; a < 10; a++) {
				System.out.print(opt[a] + " ");
			}
			Paint now = new Paint(opt1, opt, seq1, seq, SequentialSwaps,
					OptimalSwaps);
			OptimalSwaps = 0; //reset
			SequentialSwaps = 0;
		} else {
			System.out
					.println("Jonathan Allen Grant the Great's Programme Deluxariarre did not recognize your command.\nGoodbye.");
		}
	}

	//this method, as the name suggests, deletes the root of the given heap, and returns the new heap, after reheapification
	public static int[] deleteRoot(int[] maxHeap) {
		// find last index
		int a = 0;
		for (a = 0; a < 99; a++) {
			if (maxHeap[a] == 0)
				break;
		}
		// replace
		if (maxHeap[a] != 0) {
			maxHeap[0] = maxHeap[a];
			maxHeap[a] = 0;
		} else {
			maxHeap[0] = maxHeap[a - 1];
			maxHeap[a - 1] = 0;
		}
		// reheapDown
		return reheapDown(maxHeap, 0);
	}

	// take in a heap and an index and reheaps upwards until the beginning of
	// the array. returns the new array
	public static int[] reheapUp(int[] maxHeap, int i) {
		if (i == 0)
			return maxHeap;
		else if (i % 2 == 0) {
			// check against parent
			if (maxHeap[i] > maxHeap[(i - 2) / 2]) {
				// switch if index is larger than parent
				int switcherooni = maxHeap[i];
				maxHeap[i] = maxHeap[(i - 2) / 2];
				maxHeap[(i - 2) / 2] = switcherooni;
				SequentialSwaps++;
				return reheapUp(maxHeap, (i - 2) / 2);
			}
		}
		// if odd, right child
		else {
			// check against parent
			if (maxHeap[i] > maxHeap[(i - 1) / 2]) {
				// switch if index is larger than parent
				int switcherooni = maxHeap[i];
				maxHeap[i] = maxHeap[(i - 1) / 2];
				maxHeap[(i - 1) / 2] = switcherooni;
				SequentialSwaps++;
				// check bottom layers with reheap downwards
				return reheapUp(maxHeap, (i - 1) / 2);
			}
		}
		return maxHeap;
	}

	// Take in a heap, and an index to check against, and reheap downwards until
	// bottom of heap
	public static int[] reheapDown(int[] maxHeap, int i) {
		if (i >= 50
				|| (maxHeap[i] > maxHeap[i * 2 + 1] && maxHeap[i] > maxHeap[i
						* +2])) {
			return maxHeap;
		} else if (i == 49) { // has no right
			if (maxHeap[i] < maxHeap[i * 2 + 1]) {
				// left switch
				int switcherooni = maxHeap[i];
				maxHeap[i] = maxHeap[i * 2 + 1];
				maxHeap[i * 2 + 1] = switcherooni;
				OptimalSwaps++;
				maxHeap = reheapDown(maxHeap, i);
				return reheapDown(maxHeap, i * 2 + 1);
			}
		} else {
			if (maxHeap[i] < maxHeap[i * 2 + 1]) {
				// left switch
				int switcherooni = maxHeap[i];
				maxHeap[i] = maxHeap[i * 2 + 1];
				maxHeap[i * 2 + 1] = switcherooni;
				OptimalSwaps++;
				maxHeap = reheapDown(maxHeap, i);
				return reheapDown(maxHeap, i * 2 + 1);
			} else if (maxHeap[i] < maxHeap[i * 2 + 2]) {
				// right switch
				int switcherooni = maxHeap[i];
				maxHeap[i] = maxHeap[i * 2 + 2];
				maxHeap[i * 2 + 2] = switcherooni;
				OptimalSwaps++;
				maxHeap = reheapDown(maxHeap, i);
				return reheapDown(maxHeap, i * 2 + 2);
			}
		}

		return maxHeap;
	}

	
	public static int[] sequentialInsertion(int[] nums) { // takes in an array
															// of numbers and
															// returns a max
															// heap using those
															// numbers
		// fill array with nums in loop
		int[] maxHeap = new int[100];
		for (int a = 0; a < 100; a++) {
			maxHeap[a] = nums[a];
			// reheap upwards after every insertion
			maxHeap = reheapUp(maxHeap, a);
		}

		return maxHeap;
	}

	public static int[] optimalMethod(int[] nums) { // creates a max heap by
													// checking the bottom level
													// against its root, ad
													// infinitum
		int[] maxHeap = nums;

		// key:
		// parent = i
		// left = 2i + 1
		// left = i >>> parent = i / 2
		// right = 2i + 2
		// right = i >>> parent = (i - 1) / 2

		int i = 99; // due to 0 being counted as 1, and so on
		int layer = 7;
		// loop
		// start with bottom row index, check all above layers
		while (i > 0) {
			// if even, left child
			if (i % 2 == 0) {
				// check against parent
				if (maxHeap[i] > maxHeap[(i - 2) / 2]) {
					// switch if index is larger than parent
					int switcherooni = maxHeap[i];
					maxHeap[i] = maxHeap[(i - 2) / 2];
					maxHeap[(i - 2) / 2] = switcherooni;
					OptimalSwaps++;
					// check bottom layers with reheap downwards
					maxHeap = reheapDown(maxHeap, i);
				}
			}
			// if odd, right child
			else {
				// check against parent
				if (maxHeap[i] > maxHeap[(i - 1) / 2]) {
					// switch if index is larger than parent
					int switcherooni = maxHeap[i];
					maxHeap[i] = maxHeap[(i - 1) / 2];
					maxHeap[(i - 1) / 2] = switcherooni;
					OptimalSwaps++;
					// check bottom layers with reheap downwards
					maxHeap = reheapDown(maxHeap, i);
				}
			}

			// check if reached next layer
			for (int a = 0; a < 8; a++) {
				if (Math.pow(2, a) == layer) {
					layer--;
				}
			}

			i--;
		}

		return maxHeap;
	}
}