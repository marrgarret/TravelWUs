//package utilite;
//
//public class DeleteCookie {
//    public static void delete(HttpServletRequest request, HttpServletResponse response, String... name) {
//        Cookie[] cookies = request.getCookies();
//
//        for (String cookieName : name) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(cookieName)) {
//                    cookie.setValue("");
//                    cookie.setPath("/");
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//        }
//    }
//}
