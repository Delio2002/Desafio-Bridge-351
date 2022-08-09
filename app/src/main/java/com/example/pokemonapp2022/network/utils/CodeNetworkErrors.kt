package com.example.pokemonapp2022.network.utils

enum class CodeNetworkErrors(val code: Int) {
    SOCKET_TIMEOUT(901),
    UNKNOWN_HOST(902),
    CONNECTION_SHUTDOWN(903),
    IO(904),
    UNMAPPED_ERROR(999)
}