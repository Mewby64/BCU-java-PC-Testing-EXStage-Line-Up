package jogl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

import util.system.fake.FakeImage;

public class GLImage implements FakeImage {

	private final GLImage par;
	protected final TextureData data;
	protected final int[] rect;

	protected GLImage(BufferedImage b) throws IOException {
		par = null;
		data = AWTTextureIO.newTextureData(GLStatic.GLP, b, GLStatic.MIP);
		rect = new int[] { 0, 0, data.getWidth(), data.getHeight() };
	}

	protected GLImage(File is) throws IOException {
		par = null;
		data = TextureIO.newTextureData(GLStatic.GLP, is, GLStatic.MIP, "PNG");
		rect = new int[] { 0, 0, data.getWidth(), data.getHeight() };
	}

	protected GLImage(GLImage p, int... r) {
		par = p;
		data = p.data;
		rect = r;
	}

	protected GLImage(InputStream is) throws IOException {
		par = null;
		data = TextureIO.newTextureData(GLStatic.GLP, is, GLStatic.MIP, "PNG");
		rect = new int[] { 0, 0, data.getWidth(), data.getHeight() };
	}

	@Override
	public BufferedImage bimg() {
		return null;
	}

	@Override
	public int getHeight() {
		return rect[3];
	}

	public float[] getRect() {
		float[] ans = new float[4];
		int[] br = root().rect;
		ans[0] = 1.0f * rect[0] / br[2];
		ans[1] = 1.0f * rect[1] / br[3];
		ans[2] = 1.0f * rect[2] / br[2];
		ans[3] = -1.0f * rect[3] / br[3];
		ans[1] = 1 - ans[1];
		return ans;
	}

	@Override
	public int getRGB(int i, int j) {
		return 0;
	}

	@Override
	public GLImage getSubimage(int i, int j, int k, int l) {
		return new GLImage(this, i, j, k, l);
	}

	@Override
	public int getWidth() {
		return rect[2];
	}

	@Override
	public Object gl() {
		return this;
	}

	@Override
	public void setRGB(int i, int j, int p) {
	}

	protected GLImage root() {
		return par == null ? this : par.root();
	}

}
