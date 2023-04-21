package racingcar.dao.game;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Game;
import racingcar.dto.GamePlayerJoinDto;

@Component
public class RacingCarGameDBDao implements RacingCarGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarGameDBDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertGameWithKeyHolder(Game game) {
        String sql = "INSERT INTO game(play_count) VALUES(?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, game.getPlayCount());
            return ps;
        }, keyHolder);

        return Long.valueOf(String.valueOf(keyHolder.getKeys().get("game_id")));
    }

    public List<GamePlayerJoinDto> findAll() {
        String sql = "SELECT g.game_id, g.play_count, p.name, p.position, p.is_winner, p.player_id FROM GAME as g "
            + "join player as p "
            + "on g.game_id = p.game_id ";
        return jdbcTemplate.query(sql, rs -> {
                Map<Long, GamePlayerJoinDto> result = new HashMap<>();
                while (rs.next()) {
                    GamePlayerJoinDto gamePlayerJoinDto = new GamePlayerJoinDto(
                        rs.getLong("game_id"),
                        rs.getInt("play_count"),
                        rs.getString("name"),
                        rs.getInt("position"),
                        rs.getBoolean("is_winner"),
                        rs.getLong("player_id")
                    );
                    result.put(rs.getLong("player_id"), gamePlayerJoinDto);
                }
                return new ArrayList<>(result.values());
            }
        );
    }
}
