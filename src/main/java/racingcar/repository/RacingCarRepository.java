package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.dao.game.RacingCarGameDao;
import racingcar.dao.player.PlayerDao;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.ResultResponseDto;

@Repository
public class RacingCarRepository {

    private final RacingCarGameDao racingCarGameDao;
    private final PlayerDao playerDao;

    public RacingCarRepository(final RacingCarGameDao racingCarGameDao, final PlayerDao playerDao) {
        this.racingCarGameDao = racingCarGameDao;
        this.playerDao = playerDao;
    }

    public void save(RacingGameDto racingGameDto, List<PlayerDto> playerDtos) {
        Long gameId = racingCarGameDao.insertGameWithKeyHolder(new Game(racingGameDto));
        List<Player> players = playerDtos.stream()
            .map(playerDto -> new Player(playerDto.getName(), playerDto.getPosition(), gameId))
            .collect(Collectors.toList());
        playerDao.insertPlayer(players);
    }

    public List<ResultResponseDto> readGameResultAll() {
        return racingCarGameDao.findAll();
    }
}
