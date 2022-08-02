//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
    @State var text = "Waiting..."
    
    var body: some View {
        Text(text)
            .padding()
            .onAppear {
                Task {
                    do {
                        text = try await GreetingHelper().greet()
                    } catch {
                        text = error.localizedDescription
                    }
                }
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
