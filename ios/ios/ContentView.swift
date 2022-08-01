//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
    var body: some View {
        Text(GreetingHelper().greet())
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
