package net.odtel.json2m3u;

import net.odtel.json2m3u.model.Channels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AgentEndpoint {

    @GET("/api/v1/channels")
    Call<Channels> getAll(@Query("serial") String serial,
                          @Query("platform_id") String platformId,
                          @Query("rekey") String rekey,
                          @Query("order") String order);
}