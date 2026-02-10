package frc.robot.subsystems.Intake.Pivot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import edu.wpi.first.units.measure.Angle;

public class PivotConstants {
    public static final CANBus bus = new CANBus("rio");

    public static final int pivotMotorID = 32;

    public static final double GearRatio = 2.5;
    public static final Angle tolerance = Degrees.of(.5);

    public static final Slot0Configs pivotPID = new Slot0Configs()
        .withKP(7.5).withKD(0).withKS(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

    public static final MotionMagicConfigs pivotMagic = new MotionMagicConfigs()
        .withMotionMagicCruiseVelocity(RotationsPerSecond.of(40))
        .withMotionMagicAcceleration(RotationsPerSecondPerSecond.of(9));
}