# NoiseFlow

NoiseFlow is an implementation of the [Coding Challenge #24 by Daniel Shiffman](https://thecodingtrain.com/CodingChallenges/024-perlinnoiseflowfield.html).

## Description

This application visualizes particles moving through a flow field generated using Perlin noise.

## Requirements

- Java 11 or higher
- Maven 3.5 or higher

## Building and Running

### Using Maven
 Build and run the application:
   ```
   mvn clean javafx:run
   ```

## Controls

The application includes a control panel that allows you to adjust various parameters:
- Fade: Controls the fade effect of the particle trails
- Max Velocity: Sets the maximum speed of particles
- Field Influence: Determines how strongly the flow field affects particle movement
- Particle Opacity: Adjusts the opacity of particles
- Particle Hue: Changes the color hue of particles
- Particle Saturation: Modifies the color saturation of particles

## License

This project is open source and available under the [MIT License](LICENSE).
