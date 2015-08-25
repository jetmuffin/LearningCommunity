package com.lst.lc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;


public class MagickPic {
	Pipe pipeIn = null;
	InputStream in = null;
	
	public byte[] scaleImage(String url,String width,String height){
		byte[] bytes = null;
		String format = null;
		//input
		bytes = PathUtils.readPhoto(url);
		//format = url.substring(url.lastIndexOf(".") + 1).trim().toLowerCase();  
		//System.out.println(format);
		byte[] byter = null;
		
		
		try {
			byter = scaleImage(bytes,width,height,"jpg");
		} catch (IOException | InterruptedException | IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byter;
	}
	
	/**
	 * 固定宽高缩放图片
	 * @param sourceBuffer
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public byte[] scaleImage(byte[] sourceBuffer,String width, String height,String format) throws IOException,
	InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		String formatOp = format + ":-";
		byte[] imageOutData = null;
		
		try {
			op.addImage("-");
			if(width == null)
				op.thumbnail(null,Integer.valueOf(height));
			else if(height == null)
				op.thumbnail(Integer.valueOf(width), null);
			else
				op.thumbnail(Integer.valueOf(width), Integer.valueOf(height), "!");
			op.addImage(formatOp);
			
			if (format == "gif")
				op.coalesce();
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Pipe pipeOut = new Pipe(null, out);
			ConvertCmd convert = new ConvertCmd(true);
			
			in = new ByteArrayInputStream(sourceBuffer);
			pipeIn = new Pipe(in, null);
			convert.setInputProvider(pipeIn);
			convert.setOutputConsumer(pipeOut);
			convert.run(op);
		
			imageOutData = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageOutData;
	}
}
