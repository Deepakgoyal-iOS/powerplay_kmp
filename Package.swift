//
// Created by Deepak Goyal on 06/01/26.
//

// swift-tools-version:6.0
import PackageDescription

let package = Package(
    name: "XCPowerplayKMP",
    platforms: [
        .iOS(.v14),
    ],
    products: [
        .library(name: "XCPowerplayKMP", targets: ["XCPowerplayKMP"])
    ],
    targets: [
        .binaryTarget(
            name: "XCPowerplayKMP",
            path: "shared/build/XCFrameworks/release/XCPowerplayKMP.xcframework"
        )
    ]
)
