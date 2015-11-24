package org.chrisolsen.common;


public class JokeProvider {

//    private MyApi mApiService;

    public Joke fetch() {
        return new Joke("foo");
//        if(mApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
//            mApiService = builder.build();
//        }
//
//        try {
//            String text = mApiService.getJoke().execute().getData();
//            return new Joke(text);
//        } catch (IOException e) {
//            return null;
//        }
    }
}




