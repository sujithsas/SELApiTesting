package com.sel.codingstyle;

import gherkin.deps.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Created by sasisu on 3/7/2018.
 */
public class AssetTest {

    /**
     * This test checks where the asset exist in the Application
     * input a specific id to this test
     *
     * @throws ClientProtocolException
     * @throws IOException
     */

    @Test
    public void givenAssetsExists_whenAssetInfoIsRetrieved_then200IsReceived() throws ClientProtocolException, IOException {

        // Given
        final HttpUriRequest request = new HttpGet("http://localhost:8080/api/asset/1");

        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    /**
     * This test checks if the asset is not present ,API throws correct error message
     * input specific id to test this test
     *
     * @throws ClientProtocolException
     * @throws IOException
     */

    @Test
    public void givenAssetDoesntExists_whenAssetInfoIsRetrieved_then204IsReceived() throws ClientProtocolException, IOException {

        // Given
        final int id = 10;

        final HttpUriRequest request = new HttpGet("http://localhost:8080/api/asset/" + id);

        // When
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    /**
     * This test checks if the asset returns the correct Asset information when for a single asset
     * input specific id to test this test
     *
     * @throws ClientProtocolException
     * @throws IOException
     */

    @Test
    public void givenAssetExists_whenAssetInformationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        // Given
        final HttpUriRequest request = new HttpGet("http://localhost:8080/api/asset/1");

        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        // Then
        Asset resource = ResponseUtil.getResourceFromResponse(responseString, Asset.class);
        assertThat("SEL-5045 acSELerator Team Software", Matchers.is(resource.getAssetName()));
    }

    /**
     * This test checks the asset can be created providing a appropriate json string
     * input specific Asset creation json payload.
     *
     * @throws ClientProtocolException
     * @throws IOException
     */

    @Test
    public void givenAssetNotExist_whenAssetDataIsPosted_theAssetIsCreated() throws ClientProtocolException, IOException {

        //Given
        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/asset/");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        //When
        Asset asset = new Asset();
        asset.setAssetName("SEL New Device");
        asset.setAssetType("Controller");
        asset.setDetails("This is my new device");

        Gson gson = new Gson();
        String assetString = gson.toJson(asset);
        StringEntity entity = new StringEntity(assetString);
        httpPost.setEntity(entity);
        final HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);

        //Then
        assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_CREATED));


    }


}
