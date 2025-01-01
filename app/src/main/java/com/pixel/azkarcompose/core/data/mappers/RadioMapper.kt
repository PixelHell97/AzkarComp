package com.pixel.azkarcompose.core.data.mappers

import com.pixel.azkarcompose.azkar.data.dto.RadioDto
import com.pixel.azkarcompose.azkar.domain.models.RadioChannel

fun RadioDto.toRadioChannel(): RadioChannel =
    RadioChannel(
        id = id,
        name = name,
        url = url,
    )
