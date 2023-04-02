package vsvirus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayerTest {

    @Nested
    class CreateTest {

        @Test
        void 引数に名前を指定して生成することができる() {
            // Given:
            // UUIDのrandomUUID()が固定値を返すようにモックする
            var fakeUUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
            var uuidMock = Mockito.mockStatic(UUID.class);
            uuidMock.when(UUID::randomUUID).thenReturn(fakeUUID);

            // When:
            var player = Player.create("P1");

            // Then:
            assertEquals(fakeUUID, player.id);
            assertEquals("P1", player.name);
        }

    }

}
