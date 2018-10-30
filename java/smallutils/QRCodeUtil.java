package com.gaoyuan.hot.util;

public class QRCodeUtil {
//	private static final String CHARSET = "utf-8";  
//    private static final String FORMAT = "png";  
//    // 二维码尺寸  
//    private static final int QRCODE_SIZE = 94;  
//    // LOGO宽度  
//    private static final int LOGO_WIDTH = 20;  
//    // LOGO高度  
//    private static final int LOGO_HEIGHT = 20;  
//  
//    //生成二维码矩阵
//    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {  
//        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
//        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
//        hints.put(EncodeHintType.MARGIN, 0);  //删除白边
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,  
//                hints);  
//        int width = bitMatrix.getWidth();  
//        int height = bitMatrix.getHeight();  
//        BufferedImage image = new BufferedImage(width, height+36, BufferedImage.TYPE_INT_RGB);  
//        for (int x = 0; x < width; x++) {  
//            for (int y = 0; y < height+36; y++) {  
//            	if (y < 16 || y >= height+16) {
//            		image.setRGB(x, y, 0xFFFFFFFF);  
//				}else {
//					image.setRGB(x, y, bitMatrix.get(x, y-16) ? 0xFF000000 : 0xFFFFFFFF);  
//				}
//                
//            }  
//        }  
////        if (logoPath == null || "".equals(logoPath)) {  
////            return image;  
////        }  
//        // 插入图片  
////        QRCodeUtil.insertImage(image, logoPath, needCompress);  
//        // 插入文字
//        QRCodeUtil.insertString(image, content, logoPath);
//        return image;  
//    }  
//    
//    /**
//     * 插入文字
//     * @param image
//     * @param string
//     * @param string2
//     */
//    private static void insertString(BufferedImage image, String string, String string2) {
//		// TODO Auto-generated method stub
//    	Font MyFont1 = new Font("宋体", Font.PLAIN, 16);
//    	Graphics2D graph = (Graphics2D) image.getGraphics();  
//    	graph.setColor(Color.BLACK);
//    	graph.setFont(MyFont1);
//        graph.drawString(string, 4, 16);
//        graph.drawString(string2, 0, image.getHeight() - 6);
//        graph.dispose();  
//       
//	}
//
//	/** 
//     * 插入LOGO 
//     *  
//     * @param source 
//     *            二维码图片 
//     * @param logoPath 
//     *            LOGO图片地址 
//     * @param needCompress 
//     *            是否压缩 
//     * @throws Exception 
//     */  
//    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {  
//        File file = new File(logoPath);  
//        if (!file.exists()) {  
//            throw new Exception("logo file not found.");  
//        }  
//        Image src = ImageIO.read(new File(logoPath));  
//        int width = src.getWidth(null);  
//        int height = src.getHeight(null);  
//        if (needCompress) { // 压缩LOGO  
//            if (width > LOGO_WIDTH) {  
//                width = LOGO_WIDTH;  
//            }  
//            if (height > LOGO_HEIGHT) {  
//                height = LOGO_HEIGHT;  
//            }  
//            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
//            Graphics g = tag.getGraphics();  
//            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
//            g.dispose();  
//            src = image;  
//        }  
//        // 插入LOGO  
//        Graphics2D graph = source.createGraphics();  
//        int x = (QRCODE_SIZE - width) / 2;  
//        int y = (QRCODE_SIZE - height) / 2;  
//        graph.drawImage(src, x, y, width, height, null);  
//        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
//        graph.setStroke(new BasicStroke(3f));  
//        graph.draw(shape);  
//        graph.dispose();  
//    }  
//  
//    /** 
//     * 生成二维码(内嵌LOGO) 
//     *  
//     * @param content 
//     *            内容 
//     * @param logoPath 
//     *            LOGO地址 
//     * @param filePath 
//     *            存放目录 
//     * @param fileName
//     *            二维码文件名 
//     * @param needCompress 
//     *            是否压缩LOGO 
//     * @throws Exception 
//     */  
//    public static Boolean encode(String content, String logoPath, String filePath, String fileName,boolean needCompress) throws Exception {  
//    	Boolean success = false;
//		//判断文件是否存在，存在则删除
//        File file=new File(filePath + File.separator + fileName);    
//        if(file.exists()){    
//             file.delete();    
//        } 
//        
//    	BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
//        ImageIO.write(image, FORMAT, new File(filePath + File.separator + fileName));  
//        
//        if(file.exists()){    
//        	success = true;
//        }
//        
//        return success; 
//    }  
//    
//    /** 
//     * 生成二维码流(内嵌LOGO) 
//     *  
//     * @param content 
//     *            内容 
//     * @param logoPath 
//     *            LOGO地址 
//     * @param needCompress 
//     *            是否压缩LOGO 
//     * @throws Exception 
//     */  
//    public static BufferedImage encodeImg(String content, String logoPath, boolean needCompress) throws Exception {  
//        //调用方法，生成二维码矩阵
//    	BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);  
//        
//        return image; 
//    } 
}
