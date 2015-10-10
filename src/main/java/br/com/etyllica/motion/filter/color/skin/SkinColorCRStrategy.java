package br.com.etyllica.motion.filter.color.skin;

import br.com.etyllica.motion.filter.SoftPixelStrategy;
import br.com.etyllica.motion.filter.color.ColorStrategy;
import br.com.etyllica.motion.filter.color.SimpleToleranceStrategy;

/**
 * Based on: Hewa Majeed Zangana, Imad Fakhri Al Shaikhli -
 * A New Algorithm for Human Face Detection Using Skin Color Tone
 * 
 * Reference: http://www.iosrjournals.org/iosr-jce/papers/Vol11-issue6/E01163138.pdf
 */
public class SkinColorCRStrategy extends SimpleToleranceStrategy implements SoftPixelStrategy {
		
	public SkinColorCRStrategy() {
		super();
	}
	
	public SkinColorCRStrategy(int tolerance) {
		super(tolerance);
	}

	@Override
	public boolean validateColor(int rgb) {
		return isSkin(rgb, tolerance);
	}
	
	public static boolean isSkin(int rgb) {
		return isSkin(rgb, 0);
	}
	
	public static boolean isSkin(int rgb, int tolerance) {
		boolean kovacRule = SkinColorKovacStrategy.isSkin(rgb);
		
		final int R = ColorStrategy.getRed(rgb);
		final int G = ColorStrategy.getGreen(rgb);
		final int B = ColorStrategy.getBlue(rgb);
		
		final int Y  = (int)( 0.299   * R + 0.587   * G + 0.114   * B);
		final int CB = (int)(-0.16874 * R - 0.33126 * G + 0.50000 * B);
		final int CR = (int)( 0.50000 * R - 0.41869 * G - 0.08131 * B);
		
		return CR>1;
	}

	@Override
	public boolean strongValidateColor(int baseColor, int rgb) {
		return validateColor(rgb);
	}
	
}