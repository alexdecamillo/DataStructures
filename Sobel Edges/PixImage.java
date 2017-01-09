package EdgeDetection;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PixImage {
	// Buffered image to be stored (input) 
	// and to be made into edge image (output)
	BufferedImage input, output;
	
	/*
	 * Method: PixImage (Constructor)
	 * Description: Takes in an image file
	 * and places the image in a buffered image (input).
	 * Output is initialized to the dimensions of the
	 * input image
	 */
	
	public PixImage(File image){
		try {
			input = ImageIO.read(image);
		} catch (IOException e) { e.printStackTrace(); }
		output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
	}
	
	/*
	 * Method: setPixel
	 * Description: places a pixel onto a buffered image
	 * at a given location.
	 * The format for a color in the default RGB color space is
	 * ...|RRRRRRRR|GGGGGGGG|BBBBBBBB|
	 * the values passed can range from 0-255, which is less than 8-bits
	 * left shifting the bits passes the same value for red, green and blue
	 */
	void setPixel(BufferedImage img, int x, int y, int color){
		//System.out.println(Integer.toBinaryString(color << 16 | color << 8 | color));
		img.setRGB(x, y, color << 16 | color << 8 | color);
	}
	
	/*
	 * Method: sobelEdges
	 * Description: grabs and stores the RGB values of the 8 pixels 
	 * surrounding each pixel in an image. The pixels are then multiplied
	 * by a gradient filter that gives the intensities of red, green and blue.
	 * These RGB values are then combined and flattened into a 0-255 range.
	 * This is used for the gray scale to place into the same spot on the
	 * output image
	 * Returns: output image (BufferedImage)
	 */
	public BufferedImage sobelEdges(){
		int[][] pixels = new int[3][3];
		
		for(int i=1;i<input.getWidth()-1;i++){
            for(int j=1;j<input.getHeight()-1;j++){
            	pixels[0][0]=new Color(input.getRGB(i-1,j-1)).getRed();
            	pixels[0][1]=new Color(input.getRGB(i-1,j)).getRed();
            	pixels[0][2]=new Color(input.getRGB(i-1,j+1)).getRed();
            	pixels[1][0]=new Color(input.getRGB(i,j-1)).getRed();
                pixels[1][2]=new Color(input.getRGB(i,j+1)).getRed();
                pixels[2][0]=new Color(input.getRGB(i+1,j-1)).getRed();
                pixels[2][1]=new Color(input.getRGB(i+1,j)).getRed();
                pixels[2][2]=new Color(input.getRGB(i+1,j+1)).getRed();
                int redEnergy = (int) gradient(pixels);
                
                pixels[0][0]=new Color(input.getRGB(i-1,j-1)).getBlue();
            	pixels[0][1]=new Color(input.getRGB(i-1,j)).getBlue();
            	pixels[0][2]=new Color(input.getRGB(i-1,j+1)).getBlue();
            	pixels[1][0]=new Color(input.getRGB(i,j-1)).getBlue();
                pixels[1][2]=new Color(input.getRGB(i,j+1)).getBlue();
                pixels[2][0]=new Color(input.getRGB(i+1,j-1)).getBlue();
                pixels[2][1]=new Color(input.getRGB(i+1,j)).getBlue();
                pixels[2][2]=new Color(input.getRGB(i+1,j+1)).getBlue();
                int blueEnergy = (int) gradient(pixels);
                
                pixels[0][0]=new Color(input.getRGB(i-1,j-1)).getGreen();
            	pixels[0][1]=new Color(input.getRGB(i-1,j)).getGreen();
            	pixels[0][2]=new Color(input.getRGB(i-1,j+1)).getGreen();
            	pixels[1][0]=new Color(input.getRGB(i,j-1)).getGreen();
                pixels[1][2]=new Color(input.getRGB(i,j+1)).getGreen();
                pixels[2][0]=new Color(input.getRGB(i+1,j-1)).getGreen();
                pixels[2][1]=new Color(input.getRGB(i+1,j)).getGreen();
                pixels[2][2]=new Color(input.getRGB(i+1,j+1)).getGreen();
                int greenEnergy = (int) gradient(pixels);
                
                int grayScale = (int) ((redEnergy + blueEnergy + greenEnergy) / (1020/255));
                //System.out.println(grayScale);
				setPixel(output, i, j, grayScale);
            }
        }
		return output;
	}
	
	/*
	 * Method: gradient
	 * Description: This function multiplies the matrix of RBG values by the gx and gy
	 * gradient filters. The zeroes are not multiplied in the equations
	 * 		1  0 -1			 1	2  1
	 * gx = 2  0  2		gy = 0  0  0
	 * 		1  0 -1			-1 -2 -1
	 * Returns: gradient value (double)
	 */
	public static double gradient(int[][] pixelMatrix){
		int gx=(pixelMatrix[0][0]*1)+(pixelMatrix[0][2]*-1)+(pixelMatrix[1][0]*2)+
				(pixelMatrix[1][2]*-2)+(pixelMatrix[2][0]*1)+(pixelMatrix[2][2]*-1);
	    int gy=(pixelMatrix[0][0]*-1)+(pixelMatrix[0][1]*-2)+(pixelMatrix[0][2]*-1)+
	    		(pixelMatrix[2][0]*1)+(pixelMatrix[2][1]*2)+(pixelMatrix[2][2]*1);
	    return Math.sqrt(Math.pow(gx,2)+Math.pow(gy,2));
	}
	
}
