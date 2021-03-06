package com.example.zlatko.beaches.features.details.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.zlatko.beaches.R
import com.example.zlatko.beaches.base.service.location.model.Location

@Composable
internal fun DetailsView(location: Location?) {
    DetailsImage(location?.imageUrl)
    Spacer(modifier = Modifier.height(8.dp))
    DetailsText(R.string.temperature, location?.temperature)
    DetailsText(R.string.last_measurement_date, location?.lastMeasurementDate)
    DetailsText(R.string.visibility_depth, location?.visibilityDepth)
    DetailsText(R.string.rating, location?.rating)
    Column(modifier = Modifier.padding(8.dp, 2.dp)) {
        Text(text = stringResource(R.string.services), fontWeight = FontWeight.Bold)
        location?.features?.forEach { feature ->
            Text(text = stringResource(feature.text))
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun DetailsImage(imageUrl: String?) {
    Box(contentAlignment = Alignment.Center) {
        val painter = rememberImagePainter(data = imageUrl, builder = {
            error(R.drawable.ic_launcher_foreground)
        })

        if (painter.state is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

        Image(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.details_image_content_description),
        )
    }
}

@Composable
private fun DetailsText(label: Int, value: String?) {
    Row(modifier = Modifier.padding(8.dp, 2.dp)) {
        Text(text = stringResource(label), fontWeight = FontWeight.Bold)
        value?.let { Text(modifier = Modifier.padding(4.dp, 0.dp), text = it) }
    }
}