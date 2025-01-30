package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.TripPiece.domain.Map;

import java.util.List;
import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {

    // 유저 ID로 맵을 조회하는 메소드 (저장된 시간 순서로 정렬 - 최신 데이터가 리스트 마지막에 위치)
    @Query("SELECT m FROM Map m WHERE m.userId = :userId ORDER BY m.countryCode ASC, m.createdAt DESC")
    List<Map> findByUserIdOrderedByCreatedAt(Long userId);

    // 유저가 방문한 나라 수를 조회하는 메소드
    @Query("SELECT COUNT(DISTINCT m.countryCode) FROM Map m WHERE m.userId = :userId")
    long countDistinctCountryCodeByUserId(Long userId);

    // 유저가 방문한 나라의 countryCode 리스트를 조회하는 메소드
    @Query("SELECT DISTINCT m.countryCode FROM Map m WHERE m.userId = :userId")
    List<String> findDistinctCountryCodesByUserId(Long userId);

    // 유저가 방문한 도시의 city.id 리스트를 조회하는 메소드
    @Query("SELECT DISTINCT m.city.id FROM Map m WHERE m.userId = :userId")
    List<Long> findDistinctCityIdsByUserId(Long userId);

    // 유저가 방문한 도시 수를 조회하는 메소드
    @Query("SELECT COUNT(DISTINCT m.city.id) FROM Map m WHERE m.userId = :userId")
    long countDistinctCityByUserId(Long userId);

    // 유저 ID와 국가 코드, 도시 ID로 맵을 조회하는 메소드
    @Query("SELECT m FROM Map m WHERE m.userId = :userId AND m.countryCode = :countryCode AND m.city.id = :cityId")
    Optional<Map> findByUserIdAndCountryCodeAndCityId(Long userId, String countryCode, Long cityId);

    // 유저 ID와 국가 코드, 도시 ID로 중복된 모든 맵 조회
    @Query("SELECT m FROM Map m WHERE m.userId = :userId AND m.countryCode = :countryCode AND m.city.id = :cityId")
    List<Map> findAllByUserIdAndCountryCodeAndCityId(Long userId, String countryCode, Long cityId);

    // 특정 국가에서 유저가 색칠한 도시를 정렬된 순서로 조회 (최신 데이터가 리스트 마지막에 위치)
    @Query("SELECT m FROM Map m WHERE m.countryCode = :countryCode AND m.userId = :userId ORDER BY m.createdAt DESC")
    List<Map> findByCountryCodeAndUserIdOrderedByCreatedAt(String countryCode, Long userId);
}
