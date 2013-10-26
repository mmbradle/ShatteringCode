package dataset.ics;

import java.io.InputStream;
import java.util.Arrays;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.junit.Before;
import org.junit.Test;

public class PrObjTypeTest {
	byte[] prObjBuffer;
	
	@Before
	public void setup() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/" + PrObjType.FILE_NAME);
		prObjBuffer = IOUtils.toByteArray(is);
		
	}

	@Test
	public void readFile() throws Exception {
		Codec<PrObjType> PrObjTypeCodec = Codecs.create(PrObjType.class);
		PrObjType prObjType = Codecs.decode(PrObjTypeCodec, prObjBuffer);

		Assert.assertEquals(97, prObjType.getNumVarFiles());
		Assert.assertEquals(97, prObjType.getClazzes().length);
	}
	
	@Test
	public void roundTripFileToFile() throws Exception {
		byte[] expectedBuffer = prObjBuffer;
		
		Codec<PrObjType> prObjTypeCodec = Codecs.create(PrObjType.class);
		PrObjType prObjType = Codecs.decode(prObjTypeCodec, prObjBuffer);
		byte[] actualBuffer = Codecs.encode(prObjType, prObjTypeCodec);
		
		Assert.assertTrue(Arrays.equals(expectedBuffer, actualBuffer));
	}
}
