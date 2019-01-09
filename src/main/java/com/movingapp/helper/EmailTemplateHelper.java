package com.movingapp.helper;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateHelper {

    public String confirmationEmailTemplate(String name, String confirmationUrl, String email){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\">\n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\"> \n" +
                "  <title>ericgraika@gmail.com</title> \n" +
                "  <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--> \n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \n" +
                "  <style type=\"text/css\">\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { font-size:16px!important; line-height:150%!important } h1 { font-size:30px!important; text-align:center; line-height:120%!important } h2 { font-size:26px!important; text-align:center; line-height:120%!important } h3 { font-size:20px!important; text-align:center; line-height:120%!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c { text-align:center!important } .es-m-txt-r { text-align:right!important } .es-m-txt-l { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } .es-button { font-size:20px!important; display:block!important; border-width:10px 0px 10px 0px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } .es-desk-menu-hidden { display:table-cell!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social td { display:inline-block!important } table.es-social { display:inline-block!important } }\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "</style> \n" +
                " </head> \n" +
                " <body style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> \n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#CCCCCC;\"> \n" +
                "   <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#cccccc\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]--> \n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> \n" +
                "     <tr style=\"border-collapse:collapse;\"> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0;\"> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <!--[if mso]><table width=\"520\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\">Thank You For Booking!</td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"right\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\"> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-header-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"center\" style=\"padding:0;Margin:0;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"Move Muscle Logo\" title=\"Move Muscle Logo\" width=\"118\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:30px;font-style:normal;font-weight:normal;color:#4A7EB0;\">Booking Confirmation</h1> </td>  \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"5%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:2px solid #999999;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">Hi, " + name + ",</p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">You have successfully booked your move! Please confirm by clicking the link below and setting your password. Your username is: " + email +
                "</p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;\"> <span class=\"es-button-border\" style=\"border-style:solid;border-color:#4A7EB0;background:#2CB543;border-width:0px;display:inline-block;border-radius:0px;width:auto;\"> <a href=\""+ confirmationUrl + "\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none !important;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:18px;color:#4A7EB0;border-style:solid;border-color:#EFEFEF;border-width:10px 25px;display:inline-block;background:#EFEFEF;border-radius:0px;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;\">Set Password</a> </span> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">If you need help don't hesitate to&nbsp;<a target=\"_blank\" href=\"https://www.movemuscle.com/#/contact/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\">contact us</a>.</p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-right:5px;padding-top:20px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:1px solid #FFFFFF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "         </tr> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0;\"> \n" +
                "               <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"194\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"174\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"\" width=\"103\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                  <td class=\"es-hidden\" width=\"20\" style=\"padding:0;Margin:0;\"></td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#services\">Services</a></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#about\">About Us</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/login\">Login</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/contact\">Contact Us</a><br></p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с es-m-txt-l\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><span style=\"font-size:20px;line-height:30px;\">(952) - 843 - 8566<br></span></p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> \n" +
                "                       <div style=\"color:#333333;\"> \n" +
                "                        <span style=\"font-size:14px;\">contact@movemuscle.com</span> \n" +
                "                       </div> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <a target=\"_blank\" href=\"https://www.facebook.com/CollegeMovingLabor/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\"><img title=\"Facebook\" src=\"https://www.movemuscle.com/img/facebook-circle-colored.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></a></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> </td> \n" +
                "     </tr> \n" +
                "   </table> \n" +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
    }

    public String bookingMoveConfirmationEmailTemplate(String name){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\">\n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\"> \n" +
                "  <title>ericgraika@gmail.com</title> \n" +
                "  <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--> \n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \n" +
                "  <style type=\"text/css\">\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { font-size:16px!important; line-height:150%!important } h1 { font-size:30px!important; text-align:center; line-height:120%!important } h2 { font-size:26px!important; text-align:center; line-height:120%!important } h3 { font-size:20px!important; text-align:center; line-height:120%!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c { text-align:center!important } .es-m-txt-r { text-align:right!important } .es-m-txt-l { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } .es-button { font-size:20px!important; display:block!important; border-width:10px 0px 10px 0px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } .es-desk-menu-hidden { display:table-cell!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social td { display:inline-block!important } table.es-social { display:inline-block!important } }\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "</style> \n" +
                " </head> \n" +
                " <body style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> \n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#CCCCCC;\"> \n" +
                "   <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#cccccc\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]--> \n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> \n" +
                "     <tr style=\"border-collapse:collapse;\"> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0;\"> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <!--[if mso]><table width=\"520\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\">Thank You For Booking!</td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"right\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-header-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"center\" style=\"padding:0;Margin:0;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"Move Muscle Logo\" title=\"Move Muscle Logo\" width=\"118\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:30px;font-style:normal;font-weight:normal;color:#4A7EB0;\">Booking Confirmation</h1> </td>  \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"5%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:2px solid #999999;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">Hi, " + name + ",</p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">You have successfully booked your movebut you're not done yet! Please log in and complete the additional information section to confirm and&nbsp;help us better accommodate your move.</p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;\"> <span class=\"es-button-border\" style=\"border-style:solid;border-color:#4A7EB0;background:#2CB543;border-width:0px;display:inline-block;border-radius:0px;width:auto;\"> <a href=\"https://www.movemuscle.com/#/login\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none !important;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:18px;color:#4A7EB0;border-style:solid;border-color:#EFEFEF;border-width:10px 25px;display:inline-block;background:#EFEFEF;border-radius:0px;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;\">Confirm Move</a> </span> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">If you need help don't hesitate to&nbsp;<a target=\"_blank\" href=\"https://www.movemuscle.com/#/contact\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\">contact us</a>.</p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-right:5px;padding-top:20px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:1px solid #FFFFFF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "         </tr> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0;\"> \n" +
                "               <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"194\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"174\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"\" width=\"103\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                  <td class=\"es-hidden\" width=\"20\" style=\"padding:0;Margin:0;\"></td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#services/\">Services</a></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#about\">About Us</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/login\">Login</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/contact\">Contact Us</a><br></p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с es-m-txt-l\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><span style=\"font-size:20px;line-height:30px;\">(952) - 843 - 8566<br></span></p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> \n" +
                "                       <div style=\"color:#333333;\"> \n" +
                "                        <span style=\"font-size:14px;\">contact@movemuscle.com</span> \n" +
                "                       </div> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <a target=\"_blank\" href=\"https://www.facebook.com/CollegeMovingLabor/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\"><img title=\"Facebook\" src=\"https://www.movemuscle.com/img/facebook-circle-colored.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></a></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> </td> \n" +
                "     </tr> \n" +
                "   </table> \n" +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
    }

    public String chargeEmailTemplate(double charge, String creditCard){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\">\n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\"> \n" +
                "  <title>ericgraika@gmail.com</title> \n" +
                "  <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--> \n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \n" +
                "  <style type=\"text/css\">\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { font-size:16px!important; line-height:150%!important } h1 { font-size:30px!important; text-align:center; line-height:120%!important } h2 { font-size:26px!important; text-align:center; line-height:120%!important } h3 { font-size:20px!important; text-align:center; line-height:120%!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c { text-align:center!important } .es-m-txt-r { text-align:right!important } .es-m-txt-l { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } .es-button { font-size:20px!important; display:block!important; border-width:10px 0px 10px 0px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } .es-desk-menu-hidden { display:table-cell!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social td { display:inline-block!important } table.es-social { display:inline-block!important } }\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "</style> \n" +
                " </head> \n" +
                " <body style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> \n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#CCCCCC;\"> \n" +
                "   <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#cccccc\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]--> \n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> \n" +
                "     <tr style=\"border-collapse:collapse;\"> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0;\"> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <!--[if mso]><table width=\"520\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\">Thank You For Booking!</td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"250\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"250\" align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-infoblock\" align=\"right\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\"> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-header-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"center\" style=\"padding:0;Margin:0;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"Move Muscle Logo\" title=\"Move Muscle Logo\" width=\"118\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:30px;font-style:normal;font-weight:normal;color:#4A7EB0;\">Booking Confirmation</h1> </td>  \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"5%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:2px solid #999999;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">Your Credit Card ending in " + creditCard + " has been charged $" + charge + "</p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">If you need questions don't hesitate to&nbsp;<a target=\"_blank\" href=\"https://www.movemuscle.com/#/contact/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\">contact us</a>.</p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:40px;padding-right:40px;\"> \n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td width=\"520\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-right:5px;padding-top:20px;padding-bottom:20px;\"> \n" +
                "                       <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td style=\"padding:0;Margin:0px;border-bottom:1px solid #FFFFFF;background:rgba(0, 0, 0, 0) none repeat scroll 0% 0%;height:1px;width:100%;margin:0px;\"></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "         </tr> \n" +
                "         <tr style=\"border-collapse:collapse;\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "           <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\"> \n" +
                "             <tr style=\"border-collapse:collapse;\"> \n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0;\"> \n" +
                "               <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"194\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"174\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-p0l\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <img src=\"https://www.movemuscle.com/img/logo-with-text.jpg\" alt=\"\" width=\"103\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                  <td class=\"es-hidden\" width=\"20\" style=\"padding:0;Margin:0;\"></td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#services\">Services</a></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/#about\">About Us</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/login\">Login</a><br><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#333333;\" href=\"https://www.movemuscle.com/#/contact\">Contact Us</a><br></p> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td width=\"20\"></td><td width=\"173\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \n" +
                "                 <tr style=\"border-collapse:collapse;\"> \n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" width=\"173\" align=\"center\" style=\"padding:0;Margin:0;\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с es-m-txt-l\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#333333;\"><span style=\"font-size:20px;line-height:30px;\">(952) - 843 - 8566<br></span></p> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td class=\"es-m-txt-с\" esdev-links-color=\"#333333\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;\"> \n" +
                "                       <div style=\"color:#333333;\"> \n" +
                "                        <span style=\"font-size:14px;\">contact@movemuscle.com</span> \n" +
                "                       </div> </td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse;\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;\"> \n" +
                "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \n" +
                "                         <tr style=\"border-collapse:collapse;\"> \n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> <a target=\"_blank\" href=\"https://www.facebook.com/CollegeMovingLabor/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:underline;color:#4A7EB0;\"><img title=\"Facebook\" src=\"https://www.movemuscle.com/img/facebook-circle-colored.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></a></td> \n" +
                "                         </tr> \n" +
                "                       </table> </td> \n" +
                "                     </tr> \n" +
                "                   </table> </td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--> </td> \n" +
                "             </tr> \n" +
                "           </table> </td> \n" +
                "         </tr> \n" +
                "       </table> </td> \n" +
                "     </tr> \n" +
                "   </table> \n" +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
    }
}
