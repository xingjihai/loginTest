
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class CopyPath {
	
	static String filelist=
			"    U   D:/work3/wwhm/cms/com/enation/app/cms/core/service/impl/DataManager.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/member/MobileMemberCenterAction.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/goodscore/widget/goods/detail/intro.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/cart/MobileCartAction.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/widget/MemberOrderWidget.java\r\n" + 
			"    U   D:/work3/wwhm/cms/com/enation/app/cms/core/service/impl/DataManager.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/impl/FavoriteManager.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/indexNewest.css\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/member/MobileMemberAction.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/wode.css\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/coupon/service/IMemberCouponsManager.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/favorite.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/tieziinfo.css\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/section/mynotes.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/plugin/point/MemberEditPointPlugin.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/widgets_default.xml\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/section/widget/MyNotesWidget.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/widget/MemberRegisterWidget.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/widget/MemberFavoriteWidget.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/section/postBaike.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/coupon/service/MemberCouponsManager.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/impl/WeekPatLogsManager.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/searchshangpin_inc.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/weekpat/myweek_pat.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/inc/refund.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/widgets.xml\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/help/myBaike.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/inc/inc_order_list.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/notesDetail.css\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/notes/MobileNotesAction.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/mem_info.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/inc/after_sales_logistics.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/index/hot_xx2.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/impl/MemberPointManger.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/phonetextarea.css\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/weekpat/week_pat_details.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/impl/AfterSalesLogisticsManager.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/help/chanyin.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/image/icon-fatie.png\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/inc/inc_return_list.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/dingzhiinfo.css\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/forum.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/coupon/MemberCoupon.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/help/news.html\r\n" + 
			"    U   D:/work3/wwhm/cms/com/enation/app/cms/core/action/mobile/MobileSectionForMyAction.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/notesDetail.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/searchwenzhang.css\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/goods/MobileGoodsAction.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/css/chanyin.css\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/plugin/point/point.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/search_notes_inc.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/impl/NotesManager.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/searchwenzhang_inc.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/orderreturns/widget/MemberReturnsOrderWidget.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/component/member/widget/AfterSalesLogisticsWidget.java\r\n" + 
			"    U   D:/work3/wwhm/cms/com/enation/app/cms/core/action/mobile/MobileArticleAction.java\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/inc/after_sales.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/member/index.html\r\n" + 
			"    U   D:/work3/wwhm/WebContent/themes/wap/help/baikedetail_detail.html\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/service/INotesManager.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/order/MobileOrderAction.java\r\n" + 
			"    U   D:/work3/wwhm/shop/com/enation/app/shop/core/action/mobile/goods/MobileGoodsDetailAction.java"
//			""
			;
	
	static File outDir = new File("H:/out2");
	static boolean copyClass = true;// 复制源 或 class文件
	
	public static void main(String[] args) throws Exception {
		
		
		ByteArrayInputStream is = new ByteArrayInputStream(filelist.getBytes());
//		FileInputStream is = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\filelist.txt"));
		
		if(outDir.exists()){
			FileUtils.deleteDirectory(outDir);
		}
		outDir.mkdirs();
		
		List<String> lines = IOUtils.readLines(is);
		for (String l : lines) {
			try {
				if(StringUtils.isEmpty(l)){
					continue;
				}
				if(l.contains(" Skipped ")) continue;
				// src/main  /java/     -- java
				// src/main  /resources/systemConfig.properties
				// /WebRoot/WEB-INF/   -- source
				
				if(l.startsWith("#"))continue;
				//  isDir?
				if(copyClass)
					parse(l);
				else{
					String tofilepath = l.replaceFirst("/.*?/", "");//去 /yolandaSevenStar/
					
					File src = new File("./", tofilepath);
					if (src.isDirectory()) {
						FileUtils.copyDirectory(src, new File(outDir, tofilepath));
					} else {
						FileUtils.copyFile(src, new File(outDir,tofilepath));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void parse(String l) throws Exception {
		int i= 0;
		String filepath = "";
		int type = 0;
		
		do {
			i = l.indexOf("/com/");
			if (i >= 0) {
				type = 1;
				filepath = l.substring(i);
				break;
			}
			i = l.indexOf("/spring/");
			if(i>=0){
				type=2;
				filepath = l.substring(i);
				break;
			}
			i = l.indexOf("/struts-");
			if(i>=0 && l.endsWith(".xml")){
				type=2;
				filepath = l.substring(i);
				break;
			}
			i = l.indexOf("/WEB-INF/");
			if(i>=0){
				type=3;
				filepath = l.substring(i+"/WEB-INF/".length());
				break;
			}
			i = l.indexOf("/WebContent/");
			if(i>=0){
				type=4;
				filepath = l.substring(i+"/WebContent/".length());
				break;
			}
		}while(false);
		try {
			copy(type,filepath);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
//		System.out.println(filepath);
	}
	private static void copy(int type, String filepath) throws Exception {
		switch (type) {
		case 1:{
			String tofilepath = "\\WebContent\\WEB-INF/classes/"+ (filepath.replace(".java", ".class"));
			final File src = new File("./", tofilepath);
			if (src.isDirectory()) {
				FileUtils.copyDirectory(src, new File(outDir, tofilepath));
			} else {
//				FileUtils.copyFile(src, new File(outDir,tofilepath));
				//  $内部类
				FileUtils.copyDirectory(src.getParentFile(), new File(outDir,tofilepath).getParentFile(), new FileFilter() {
					@Override
					public boolean accept(File f) {
						if(f.getName().equals(src.getName())){
							return true;
						}
						if(f.getName().startsWith(src.getName().replace(".class", "$"))){
							return true;
						}
						return false;
					}
				});
			}
			break;
		}
		case 2:{
			String tofilepath = "\\WebContent\\WEB-INF/classes/"+filepath;
			File src = new File("./", tofilepath);
			if (src.isDirectory()) {
				FileUtils.copyDirectory(src, new File(outDir, tofilepath));
			} else {
				FileUtils.copyFile(src, new File(outDir, tofilepath));
			}
			break;
		}
		case 3:{
			String tofilepath = "\\WebContent\\WEB-INF/"+ filepath;
			File src = new File("./", tofilepath);
			if(src.isDirectory()){
				FileUtils.copyDirectory(src, new File(outDir,tofilepath));
			} else
				FileUtils.copyFile(src, new File(outDir,tofilepath));
			break;
		}
		case 4:{
			String tofilepath = "\\WebContent/"+ filepath;
			File src = new File("./", tofilepath);
			if(src.isDirectory()){
				FileUtils.copyDirectory(src, new File(outDir,tofilepath));
			} else
				FileUtils.copyFile(src, new File(outDir,tofilepath));
			break;
		}
		default:
			break;
		}
	}
}
