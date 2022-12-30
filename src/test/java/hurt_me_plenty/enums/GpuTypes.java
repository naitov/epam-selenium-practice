package hurt_me_plenty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GpuTypes {
    NVIDIA_P4("NVIDIA TESLA P4", "NVIDIA_TESLA_P4"),
    NVIDIA_V100("NVIDIA TESLA V100", "NVIDIA_TESLA_V100");

    private final String name;
    private final String value;
}
