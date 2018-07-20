package net.odtel.json2m3u.service;

import lombok.extern.slf4j.Slf4j;
import net.odtel.json2m3u.AgentEndpoint;
import net.odtel.json2m3u.model.Channel;
import net.odtel.json2m3u.model.Channels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class M3uServiceImpl implements M3uService {

    @Value("${bonus-tv.serialKey}")
    private String serialKey;

    @Value("${bonus-tv.rekey:true}")
    private String rekey;

    @Value("${bonus-tv.platformId:5}")
    private String platformId;

    @Value("${bonus-tv.order:asc}")
    private String order;

    private AgentEndpoint agentEndpoint;

    @Autowired
    public M3uServiceImpl(AgentEndpoint agentEndpoint) {
        this.agentEndpoint = agentEndpoint;
    }

    @Override
    public String getAll() {
        StringBuilder stringBuffer = new StringBuilder();

        Call<Channels> call = agentEndpoint.getAll(serialKey, platformId, rekey, order);
        log.info("URL request=" + call.request().toString());
        try {
            retrofit2.Response<Channels> execute = call.execute();
            if (execute.isSuccessful()) {
                Channels body = execute.body();
                if (body != null) {
                    List<Channel> channels = body.getChannels();
                    log.info("Channels: " + channels.size());
                    for (Channel channel : channels) {
                        stringBuffer.append("#EXTINF:0,").append(channel.getName()).append("\r\n");
                        stringBuffer.append(channel.getUrl()).append("\r\n");
                    }
                    return stringBuffer.toString();
                }
            } else {
                log.error("Error" + execute.raw().toString());
            }

        } catch (IOException e) {
            log.error("Error " + e);
        }

        return null;

    }
}
