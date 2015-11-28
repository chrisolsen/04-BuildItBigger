/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package org.chrisolsen.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.chrisolsen.org",
    ownerName = "backend.chrisolsen.org",
    packagePath=""
  )
)
public class MyEndpoint {

    private static class JokeGenerator {

        static String[] jokes = new String[] {
                "A computer once beat me at chess, but it was no match for me at kick boxing.",
                "As long as there are tests, there will be prayer in schools.",
                "What did one ocean say to the other ocean? Nothing, they just waved.",
                "A day without sunshine is like, night.",
                "A bank is a place that will lend you money, if you can prove that you don’t need it."
        };

        public static String generate() {
            int index = (int)(Math.random() * jokes.length);
            return jokes[index];
        }
    }

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();

        String joke = JokeGenerator.generate();
        response.setData(joke);
        return response;
    }
}
