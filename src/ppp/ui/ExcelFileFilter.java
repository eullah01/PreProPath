package ppp.ui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Ehsan Ullah
 */
public class ExcelFileFilter extends FileFilter {

   @Override
   public boolean accept(File f) {
      return f.isDirectory() || f.getName().toLowerCase().endsWith(".xls");
   }

   @Override
   public String getDescription() {
      return "Microsoft Excel Workbook (*.xls)";
   }
}
