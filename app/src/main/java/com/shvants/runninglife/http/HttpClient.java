package com.shvants.runninglife.http;


public class HttpClient {

//    public static OkHttpClient getUnsafeOkHttpClient() {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(final java.security.cert.X509Certificate[] chain,
//                                                       final String authType) {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(final java.security.cert.X509Certificate[] chain,
//                                                       final String authType) {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//            final OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(final String hostname, final SSLSession session) {
//                    return true;
//                }
//            });
//
//            final OkHttpClient okHttpClient = builder.build();
//            return okHttpClient;
//        } catch (final Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}