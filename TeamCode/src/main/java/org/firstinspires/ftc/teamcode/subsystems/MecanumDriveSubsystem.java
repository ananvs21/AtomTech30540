package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDriveSubsystem extends SubsystemBase {
        private final DcMotorEx frontLeft;
        private final DcMotorEx backLeft;
        private final DcMotorEx frontRight;
        private final DcMotorEx backRight;
        private final IMU imu;
        private static final double STRAFE_GAIN = 1.1;
        private double robotHeading = 0.0;
        public MecanumDriveSubsystem(HardwareMap hardwareMap) {
            frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
            backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
            frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
            backRight = hardwareMap.get(DcMotorEx.class, "backRight");

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);

            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            setMotorsRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            imu = hardwareMap.get(IMU.class, "imu");
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
            imu.initialize(parameters);
        }
        @Override
        public void periodic() {
            robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }
        public void drive(double forward, double strafe, double turn) {
            double botHeading = this.robotHeading;

            // Rotate the movement direction counter to the bot's rotation
            double rotstrafe = strafe * Math.cos(-botHeading) - forward * Math.sin(-botHeading);
            double rotforward = strafe * Math.sin(-botHeading) + forward * Math.cos(-botHeading);

            rotstrafe = rotstrafe * STRAFE_GAIN;
            double frontLeftPower = rotforward + rotstrafe + turn;
            double backLeftPower = rotforward - rotstrafe + turn;
            double frontRightPower = rotforward - rotstrafe - turn;
            double backRightPower = rotforward + rotstrafe - turn;

            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
        }

        public void stop() {
            drive(0, 0, 0);
        }

        public void setMotorsRunMode(DcMotor.RunMode mode) {
            frontLeft.setMode(mode);
            backLeft.setMode(mode);
            frontRight.setMode(mode);
            backRight.setMode(mode);
        }
    // Função vital para o piloto resetar o "norte" do campo se a IMU driftar
    public void resetYaw() {
        imu.resetYaw();
    }
    }
