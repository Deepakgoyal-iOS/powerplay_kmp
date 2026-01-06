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
            url: "https://github.com/Deepakgoyal-iOS/powerplay_kmp/releases/download/v1.0/XCPowerplayKMP.xcframework.zip",
            checksum:"ff6031679489559c934e518aa8e4214634a7dda2a68b18ee31ca5d0473d8f6bd")
    ]
)
